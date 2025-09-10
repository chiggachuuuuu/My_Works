import requests
import datetime
import asyncio
import aiohttp

today = datetime.date.today()

with open ("listofthousandtickers.txt", "r") as file:
    tickers = file.read().splitlines()

async def fetch(session, url):
    async with session.get(url, ssl=False) as response:
        return await response.json()

async def make_requests():
    async with aiohttp.ClientSession() as session:
        tasks = []
        for ticker in tickers:
            url = f'https://api.polygon.io/v1/open-close/{ticker}/{today}?adjusted=true&apiKey=1SEwFpxQSiQqHp2soaX0QSYtGpVitHoz'
            tasks.append(asyncio.ensure_future(fetch(session, url)))
        responses = await asyncio.gather(*tasks)
        return responses

loop = asyncio.get_event_loop()
results = loop.run_until_complete(make_requests())

for result in results:
    if 'open' not in result:
        pass
    else:
        print(f"{result['symbol']}: {result['open']}")
