import requests 
import datetime
from dateutil.relativedelta import relativedelta

def get_data(ticker):
    current_date = datetime.date.today()
    past_date = current_date - relativedelta(months=1)
    return requests.get(f'https://api.polygon.io/v2/aggs/ticker/{ticker}/range/1/day/{past_date}/{current_date}?adjusted=true&sort=asc&apiKey=1SEwFpxQSiQqHp2soaX0QSYtGpVitHoz').json()
def trendupor(prices):
    return prices[0] < prices[1] < prices[2] < prices[3] < prices[4]
def trenddownor(prices):
    return prices[0] > prices[1] > prices[2] > prices[3] > prices[4]

with open ("listofthousandtickers.txt", "r") as file:
    tickers = file.read().splitlines()
print(len(tickers))

trendup = []
trenddown = []
for ticker in tickers:
    data = get_data(ticker)
    if ('results' not in data.keys()):
        pass
    else:
        results = data['results']
    prices = [result['c'] if ('c' in result.keys()) else '' for result in results]
    prices = list(filter(lambda x: x != '', prices))[-5:]
    if (len(prices) < 5):
        pass
    else:
        if trendupor(prices):
            trendup.append(ticker)
        elif trenddownor(prices):
            trenddown.append(ticker)
        else:
            pass

print("num of trend up: ", len(trendup), " num of trend down: ", len(trenddown))
