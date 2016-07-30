import requests, bs4, urlparse, ftplib

ht = 0

def download_file(url):
    local_filename = url.split('/')[-1]
    size = 0
    r = requests.get(url, stream=True)
    with open(local_filename, 'wb') as f:
        for chunk in r.iter_content(chunk_size=1024): 
            if chunk:
                f.write(chunk)
                size = size + chunk;
                print "Downloaded: " + str(size) + "bytes"
                #f.flush() check
    return local_filename

def check_timer():
	page = requests.get("http://barca.tk/links.txt")
	lines = page.content.split('\n')
	print str(len(lines))

check_timer()
	
# u_name = 'u157992303'
# passwd = 'ritesh@6969'

# server = ftplib.FTP("31.220.16.14")
# server.login(u_name, passwd)
# server.dir()




# page = requests.get("http://barca.tk/links.txt")
# lines = page.content.split('\n')

# for i in lines:
# 	dis = i.split(' ')
# 	# Download dis[0] and change dis[2]
# 	download_file(dis[0])
# 	print "Download Complete!"
# 	# Change the flag