import datetime
from dateutil.relativedelta import relativedelta 
import time 
import requests
import pandas as pd
import mplfinance as mpf

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
    data = requests.get(f'https://api.polygon.io/v2/aggs/ticker/{ticker}/range/1/minute/{starttime}/{endtime}?adjusted=true&sort=asc&apiKey=1SEwFpxQSiQqHp2soaX0QSYtGpVitHoz').json()
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

ticker = input('Enter Ticker Name to Plot Minute Data:')
minute_data(ticker)
