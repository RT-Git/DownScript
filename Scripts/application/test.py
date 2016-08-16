import downloader as d


l = "https://www.youtube.com/watch?v=963TE8mh8VA"
l2 = "http://randomusik.tk/songs/4.mp3"
first = d.download(l2)

print first.get_headers()['content-length']

first.start_download()

# import requests

# r = requests.get("http://randomusik.tk/songs/4.mp3")

# with open('test.mp3', 'wb') as f:
# 	for chunk in r.iter_content(chunk_size=1024): 
# 		if chunk:
# 			f.write(chunk)
