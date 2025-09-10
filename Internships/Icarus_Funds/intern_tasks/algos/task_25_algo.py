# Imports
import requests
from dateutil.relativedelta import relativedelta
import datetime
import statistics
import datetime
import time 
import pandas as pd
import mplfinance as mpf
import asyncio
import aiohttp

current_date = datetime.date.today()
past_date = current_date - relativedelta(months=3)

with open ("listofalltickers.txt", "r") as file:
    tickers = file.read().splitlines()

async def fetch(session, url):
    async with session.get(url, ssl=False) as response:
        return await response.json()

async def make_requests(tickers):
    async with aiohttp.ClientSession() as session:
        tasks = []
        for ticker in tickers:
            url = f'https://api.polygon.io/v2/aggs/ticker/{ticker}/range/1/day/{past_date}/{current_date}?adjusted=true&sort=asc&apiKey=1SEwFpxQSiQqHp2soaX0QSYtGpVitHoz'
            tasks.append(asyncio.ensure_future(fetch(session, url)))
        responses = await asyncio.gather(*tasks)
        return responses
class Stock:
    def __init__(self, ticker, volumemeanfivedays, volumemeanthreemonths, zeroday, oneday, twoday, threeday, fourday): 
        self.ticker = ticker
        self. volumemeanfivedays = volumemeanfivedays
        self.volumemeanthreemonths = volumemeanthreemonths
        self.zeroday = zeroday
        self.oneday = oneday
        self.twoday = twoday
        self.threeday = threeday
        self.fourday = fourday
def high_vol_or(results):
    vol = [result['v'] if ('v' in result.keys()) else '' for result in results]
    vol = list(filter(lambda x: x != '', vol))
    if (len(vol) < 5):
        return (False, 0, 0)
    else:
        vol_mean = statistics.mean(vol)
        past_five = statistics.mean(vol[-5:])
        return (past_five > vol_mean, past_five, vol_mean)
def trendupor(prices):
    return prices[0] < prices[1] < prices[2] < prices[3] < prices[4]
def trenddownor(prices):
    return prices[0] > prices[1] > prices[2] > prices[3] > prices[4]
def day_data(data):
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
    day_data = pd.DataFrame()
    day_data['open'] = op
    day_data['close'] = cp
    day_data['high'] = hp
    day_data['low'] = lp
    day_data['volume'] = vol
    day_data.index = pd.to_datetime(ts, unit='ms').tz_localize('UTC').tz_convert('America/New_York')
    ticker = data['ticker']
    fig, ax = mpf.plot(day_data, type='candle', style="binance", ylabel='Price', volume=False, returnfig=True)
    ax[0].set_title(f'{ticker}')
    ax[0].set_xticklabels([])
    mpf.show()
    return

loop = asyncio.get_event_loop()
results = loop.run_until_complete(make_requests(tickers))

trendup = []
trenddown = []
for result in results:
    if 'results' not in result:
        pass
    else:
        highvolor, volumemeanfivedays, volumemeanthreemonths = high_vol_or(result['results'])
        if highvolor:
            prices = [daily['c'] if ('c' in daily.keys()) else '' for daily in result['results']]
            prices = list(filter(lambda x: x != '', prices))[-5:]
            zeroday = prices[4]
            oneday = prices[3]
            twoday = prices[2]
            threeday = prices[1]
            fourday = prices[0]
            if trendupor(prices):
                trendup.append(Stock(result['ticker'], volumemeanfivedays, volumemeanthreemonths, zeroday, oneday, twoday, threeday, fourday))
            elif trenddownor(prices):
                trenddown.append(Stock(result['ticker'], volumemeanfivedays, volumemeanthreemonths, zeroday, oneday, twoday, threeday, fourday))
            else:
                pass
        else:
            pass
trendup = [stock.ticker for stock in trendup] 
trenddown = [stock.ticker for stock in trenddown]
print(trendup)
print('num of trendup: ', len(trendup))
print(trenddown)
print('num of trenddown: ', len(trenddown))

loop = asyncio.get_event_loop()
ups = loop.run_until_complete(make_requests(trendup))
loop = asyncio.get_event_loop()
downs = loop.run_until_complete(make_requests(trenddown))
for up in ups:
    day_data(up)
for down in downs:
    day_data(down)
