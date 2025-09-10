# Imports
import requests
from dateutil.relativedelta import relativedelta
import datetime
import statistics

def get_vol_stats(ticker):
    current_date = datetime.date.today()
    past_date = current_date - relativedelta(months=3)
    data = requests.get(f'https://api.polygon.io/v2/aggs/ticker/{ticker}/range/1/day/{past_date}/{current_date}?adjusted=true&sort=asc&apiKey=1SEwFpxQSiQqHp2soaX0QSYtGpVitHoz').json()
    if ('results' not in data.keys()):
        print(f'Results not found for {ticker}')
        return
    else:
        results = data['results']
        vol = [result['v'] if ('v' in result.keys()) else '' for result in results]
        vol = list(filter(lambda x: x != '', vol))
        vol_mean = statistics.mean(vol)
        vol_std = statistics.stdev(vol)
        print(f"{ticker}'s current volume: {vol[-1]}")
        print(f"{ticker}'s average volume over the past 3 months: {vol_mean}")
        print(f"{ticker}'s standard deviation of volume over the past 3 months: {vol_std}")

ticker = input("Enter Ticker Name to see its Volume stats: ")
get_vol_stats(ticker)
