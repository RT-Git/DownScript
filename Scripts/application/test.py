import downloader as d


first = d.download("http://randomusik.tk/songs/9.mp3")

print first.get_headers()['content-length']

first.start()