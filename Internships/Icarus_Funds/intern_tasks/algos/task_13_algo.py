# Imports
import requests
from dateutil.relativedelta import relativedelta
import datetime
import statistics

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
with open ("listofthousandtickers.txt", "r") as file:
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
