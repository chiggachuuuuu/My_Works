import datetime
from dateutil.relativedelta import relativedelta 
import time
import pandas as pd 
import mplfinance as mpf 
import matplotlib.pyplot as plt
import asyncio
import aiohttp
import matplotlib
matplotlib.use('TkAgg')

today = datetime.date.today()

class Stock:
    def __init__(self, ticker, todayopen, todayclose):
        self.ticker = ticker
        self.todayopen = todayopen
        self.todayclose = todayclose
        self.difference = (self.todayclose - self.todayopen) / self.todayopen
        
async def fetch(session, url):
    async with session.get(url, ssl=False) as response:
        return await response.json()

async def make_requests():
    async with aiohttp.ClientSession() as session:
        tasks = []
        for ticker in tickers:
            url = f'https://api.polygon.io/v2/aggs/ticker/{ticker}/range/1/day/{today}/{today}?adjusted=true&sort=asc&apiKey=1SEwFpxQSiQqHp2soaX0QSYtGpVitHoz'
            tasks.append(asyncio.ensure_future(fetch(session, url)))
        responses = await asyncio.gather(*tasks)
        return responses
def minute_data(i, data):
    ticker = data['ticker']
    if ('results' not in data.keys()):
        print(f'Results not found for {ticker}')
        return
    else:
        results = data['results']
        op = [result['o'] if ('o' in result.keys()) else '' for result in results]
        op = list(filter(lambda x: x != '', op))
        cp = [result['c'] if ('c' in result.keys()) else '' for result in results]
        cp = list(filter(lambda x: x != '', cp))
        hp = [result['h'] if ('h' in result.keys()) else '' for result in results]
        hp = list(filter(lambda x: x != '', hp))
        lp = [result['l'] if ('l' in result.keys()) else '' for result in results]
        lp = list(filter(lambda x: x != '', lp))
        vol = [result['v'] if ('v' in result.keys()) else '' for result in results]
        vol = list(filter(lambda x: x != '', vol))
        ts = [result['t'] if ('t' in result.keys()) else '' for result in results]
        ts = list(filter(lambda x: x != '', ts))
        minute_data = pd.DataFrame()
        minute_data['open'] = op
        minute_data['close'] = cp
        minute_data['high'] = hp
        minute_data['low'] = lp
        minute_data['volume'] = vol
        minute_data.index = pd.to_datetime(ts, unit='ms').tz_localize('UTC').tz_convert('America/New_York')
        row, col = divmod(i, 5)
        ax = axs[row, col]
        mpf.plot(minute_data, type='candle', style='binance', ax=ax, volume=False, ylabel="")
        ax.set_title(first_15[i])
        ax.xaxis.set_visible(False)
        return

with open ("listofalltickers.txt", "r") as file:
    tickers = file.read().splitlines()


loop = asyncio.get_event_loop()
results = loop.run_until_complete(make_requests())

stocks = []

for result in results:
    if 'results' not in result:
        pass
    else:
        prices = result['results'][0]
        if ('o' not in prices) or ('c' not in prices):
            pass
        else:
            symbol = result['ticker']
            stocks.append(Stock(symbol, prices['o'], prices['c']))
stocks.sort(key=lambda x: x.difference, reverse=True)
tickers_sorted = [stock.ticker for stock in stocks]
first_15 = tickers_sorted[:15]
for stock in stocks[:15]:
    print(f'{stock.ticker}: open: {stock.todayopen} close: {stock.todayclose} Difference: {stock.difference}')
print(first_15)

async def make_minute_requests(first_15):
    async with aiohttp.ClientSession() as session:
        tasks = []
        start_time = time.localtime()
        y = list(start_time)
        y[3] = 9
        y[4] = 30
        y[5] = 0
        start_time = tuple(y)
        starttime = int(time.mktime(start_time) * 1000)
        end_time = time.localtime()
        z = list(end_time)
        z[3] = 16
        z[4] = 0
        z[5] = 0
        end_time = tuple(z)
        endtime = int(time.mktime(end_time) * 1000)
        for ticker in first_15:
            url = f'https://api.polygon.io/v2/aggs/ticker/{ticker}/range/1/minute/{starttime}/{endtime}?adjusted=true&sort=asc&apiKey=1SEwFpxQSiQqHp2soaX0QSYtGpVitHoz'
            tasks.append(asyncio.ensure_future(fetch(session, url)))
        responses = await asyncio.gather(*tasks)
        return responses

minute_results = loop.run_until_complete(make_minute_requests(first_15))
print(minute_results)

fig, axs = plt.subplots(3, 5)
#plt.subplots_adjust(wspace=0.4)

for i, r in enumerate(minute_results):
    minute_data(i, r)
    
axs = axs.flatten()
manager = plt.get_current_fig_manager()
manager.full_screen_toggle()
#plt.subplots_adjust(wspace=0.4)
plt.tight_layout()
plt.show()
