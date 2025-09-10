# Imports
import requests
from dateutil.relativedelta import relativedelta
import datetime
import statistics
import datetime
import time 
import pandas as pd
import mplfinance as mpf

def high_vol_or(ticker):
    current_date = datetime.date.today()
    past_date = current_date - relativedelta(months=3)
    data = requests.get(f'https://api.polygon.io/v2/aggs/ticker/{ticker}/range/1/day/{past_date}/{current_date}?adjusted=true&sort=asc&apiKey=1SEwFpxQSiQqHp2soaX0QSYtGpVitHoz').json()
    if ('results' not in data.keys()):
        return False
    else:
        results = data['results']
        vol = [result['v'] if ('v' in result.keys()) else '' for result in results]
        vol = list(filter(lambda x: x != '', vol))
        if (len(vol) < 5):
            return False
        else:
            vol_mean = statistics.mean(vol)
            past_five = statistics.mean(vol[-5:])
            return past_five > vol_mean
def add_high_vol_tick(tickers):
    for ticker in tickers:
        if high_vol_or(ticker):
            high_vol_ticks.append(ticker)
        else:
            pass

def trendupor(prices):
    return prices[0] < prices[1] < prices[2] < prices[3] < prices[4]
def trenddownor(prices):
    return prices[0] > prices[1] > prices[2] > prices[3] > prices[4]

def get_data(ticker):
    current_date = datetime.date.today()
    past_date = current_date - relativedelta(months=3)
    return requests.get(f'https://api.polygon.io/v2/aggs/ticker/{ticker}/range/1/day/{past_date}/{current_date}?adjusted=true&sort=asc&apiKey=1SEwFpxQSiQqHp2soaX0QSYtGpVitHoz').json()
with open ("listofalltickers.txt", "r") as file:
    tickers = file.read().splitlines()
    
high_vol_ticks = []
add_high_vol_tick(tickers)

trendup = []
trenddown = []
for ticker in high_vol_ticks:
    data = get_data(ticker)
    if ('results' not in data.keys()):
        pass
    else:
        results = data['results']
    prices = [result['c'] if ('c' in result.keys()) else '' for result in results]
    prices = list(filter(lambda x: x != '', prices))[-5:]
    if trendupor(prices):
        trendup.append(ticker)
    elif trenddownor(prices):
        trenddown.append(ticker)
    else:
        pass
print("num of trend up: ", len(trendup), " num of trend down: ", len(trenddown))
print("trend up: ", trendup)
print("trend down: ", trenddown)
def minute_data(ticker):
    start_time = time.localtime()
    y = list(start_time)
    y[3] = 9
    y[4] = 30
    y[5] = 0
    start_time = tuple(y)
    starttime = int(time.mktime(start_time)*1000)
    end_time = time.localtime()
    z = list(end_time)
    z[3] = 16
    z[4] = 0
    z[5] = 0
    end_time = tuple(z)
    endtime = int(time.mktime(end_time)*1000)
    data = requests.get(f'https://api.polygon.io/v2/aggs/ticker/{ticker}/range/1/minute/{starttime}/{endtime}?adjusted=true&sort=asc&apiKey=o1vSM098G66T3VclfPp142RF6hkNdfgI').json()
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
        #minute_data['volume'] = vol
        minute_data.index = pd.to_datetime(ts, unit='ms').tz_localize('UTC').tz_convert('America/New_York')
        fig, ax = mpf.plot(minute_data, type='candle', style="binance",
                 #title=f'Minute Data for {ticker}',
                 ylabel='Price',
                 volume=False,
                 returnfig=True,
                 #update_width_config=dict(candle_linewidth=0.5, candle_width=0.5),
        )
        ax[0].set_title(f'{ticker}')
        ax[0].set_xticklabels([])
        mpf.show()
        return

for ticker in trendup:
    minute_data(ticker)
for ticker in trenddown:
    minute_data(ticker)
