import requests
import time
print(time.asctime())

with open ("listofthousandtickers.txt", "r") as file:
    tickers = file.read().splitlines()
print(len(tickers))

def getter(ticker):
    data = requests.get(f'https://api.polygon.io/v2/snapshot/locale/us/markets/stocks/tickers/{ticker}?apiKey=1SEwFpxQSiQqHp2soaX0QSYtGpVitHoz').json()
    if ('ticker' not in data.keys()):
        print(f'data not found for {ticker}')
    else:
        tick = data['ticker']
        if ('day' not in tick.keys()):
            print(f'parameters not found for {ticker}')
        else:
            day = tick['day']
            print(f'{ticker}: ')
            if ('h' not in day.keys()):
                print(f'Highest Price not found for {ticker}')
            else:
                hp = day['h']
                print(f'Highest Price: {hp}')
            if ('o' not in day.keys()):
                print(f'Open Price not found for {ticker}')
            else:
                op = day['o']
                print(f'Open Price: {op}')
            if ('v' not in day.keys()):
                print(f'Volume not found for {ticker}')
            else:
                vol = day['v']
                print(f'Volume: {vol}')

for ticker in tickers:
    getter(ticker)

print(time.asctime())
