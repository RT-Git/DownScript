import requests as rq


l_path = "/home/vaibhav3301/Downloads/"
def get_headers(link):
	response = rq.head(link)
	return response.headers

def start_download(url):
    local_filename = url.split('/')[-1]
    # NOTE the stream=True parameter
    r = rq.get(url, stream=True)
    print r.headers['content-length']
    with open(l_path+local_filename, 'wb') as f:
        for chunk in r.iter_content(chunk_size=1024): 
            if chunk: # filter out keep-alive new chunks
                f.write(chunk)