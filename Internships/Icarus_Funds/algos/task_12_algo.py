# Imports
import requests
from dateutil.relativedelta import relativedelta
import datetime
import statistics

with open ("listofthousandtickers.txt", "r") as file:
    tickers = file.read().splitlines()
high_vol_ticks = []
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
        vol_mean = statistics.mean(vol)
        vol_std = statistics.stdev(vol)
        curr_vol = vol[-1]
        return curr_vol > vol_mean + 3 * vol_std
def add_high_vol_tick(tickers):
    for ticker in tickers:
        if high_vol_or(ticker):
            high_vol_ticks.append(ticker)
        else:
            pass
add_high_vol_tick(tickers)
print(high_vol_ticks)
