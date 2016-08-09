import requests, bs4, urlparse, ftplib, time

ht = 0
links_downloaded = 0
myfile = "http://barca.tk/links.txt"

def download_file(url):
    local_filename = url.split('/')[-1]
    print "Downloading new file..."
    r = requests.get(url)
    with open(local_filename, 'wb') as f:
        for chunk in r.iter_content(chunk_size=1024): 
            if chunk:
                f.write(chunk)
    return local_filename

while 1:
    try:
        page = requests.get(myfile)
        lines = page.content.split('\n')
        c_ht = len(lines) - 1
        #print lines
        if not ht == c_ht:
            ht = c_ht
            try:
                while not links_downloaded == ht:
                    download_file(lines[links_downloaded])
                    print "Download Complete!"
                    links_downloaded = links_downloaded + 1
            except:
                print "Download error in link no. " + str(links_downloaded+1)
                links_downloaded = links_downloaded + 1
                continue
        else:
            print "Checking..."
        time.sleep(1)
    except:
        continue