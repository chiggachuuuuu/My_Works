# Imports
import requests
import matplotlib.pyplot as plt
import datetime
import statistics
import pandas as pd

ticker = input("Enter ticker name to get minute volume and price plot: ")
today = datetime.date.today()

data = requests.get(f'https://api.polygon.io/v2/aggs/ticker/{ticker}/range/1/minute/{today}/{today}?adjusted=true&sort=asc&apiKey=1SEwFpxQSiQqHp2soaX0QSYtGpVitHoz').json()
results = data['results']
# Minute Prices Lists
prices = [result['o'] if ('o' in result.keys()) else result['c'] if ('c' in result.keys()) else '' for result in results]
prices = list(filter(lambda x: x != '', prices))
#volume list
volumes = [result['v'] if ('v' in result.keys()) else '' for result in results]
volumes = list(filter(lambda x: x != '', volumes))
volumes_mean = statistics.mean(volumes)
volumes_std = statistics.stdev(volumes)
prices_mean = statistics.mean(prices)
prices_std = statistics.stdev(prices)
vol_upper = volumes_mean + 3 * volumes_std
vol_lower = volumes_mean - 3 * volumes_std 
price_upper = prices_mean + 3 * prices_std
price_lower = prices_mean - 3 * prices_std
prices = list(filter(lambda x: (x >= price_lower) and (x <= price_upper), prices))
volumes =  list(filter(lambda x: (x >= vol_lower) and (x <= vol_upper), volumes))
timestamps = [datetime.datetime.fromtimestamp(entry["t"]/1000) for entry in data['results']]

def aggregate_volume(timestamps, volumes, interval=5):
    df = pd.DataFrame({'timestamp': timestamps, 'volume': volumes})
    df.set_index('timestamp', inplace=True)
    df = df.resample(f'{interval}T').sum()
    return df.index, df['volume']

agg_timestamps, agg_volumes = aggregate_volume(timestamps[:len(volumes)], volumes, interval=5)

   
volume_colors = ['orange']  # Initial volume has no previous value to compare with
#for i in range(1, len(agg_volumes)):
    #if agg_volumes.iloc[i] > agg_volumes.iloc[i-1]:
        #volume_colors.append('red')
    #else:
        #volume_colors.append('green')

fig, ax1 = plt.subplots(figsize=(14, 7))
color = 'tab:blue'
ax1.set_xlabel('Time')
ax1.set_ylabel('Price', color=color)
ax1.plot(timestamps[:len(prices)], prices, color=color, label='Price', linewidth=2)
ax1.tick_params(axis='y', labelcolor=color)
ax1.grid(True)

ax2 = ax1.twinx()
ax2.set_ylabel('Volume', color='black')  
ax2.bar(agg_timestamps, agg_volumes, color=volume_colors, alpha=0.5, width=0.003)  
ax2.tick_params(axis='y', labelcolor='black')

# Set higher y-axis limits for volume
ax2.set_ylim(0, max(agg_volumes) * 1.5)  # Increase the upper limit by 50%
fig.tight_layout(pad=3.0, h_pad=2.0, w_pad=2.0)
fig.suptitle(f"Minute by Minute Price and Volume for {ticker}", fontsize=16)
ax1.legend(loc='upper left')

plt.show()
