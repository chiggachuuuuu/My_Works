import requests
import datetime
import asyncio
import aiohttp

today = datetime.date.today()

class Stock:
    def __init__(self, ticker, todayopen, todayclose) :
        self.ticker = ticker
        self.todayopen = todayopen
        self.todayclose = todayclose
        self.difference = (todayclose - todayopen) / todayopen
        
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

with open ("listofthousandtickers.txt", "r") as file:
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
print(first_15)
