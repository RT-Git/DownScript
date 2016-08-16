import requests as rq

class download(object):

	link = ""
	headers = ""
	
	def __init__(self, link):
		self.link = link

	def get_headers(self):
		response = rq.head(link)
		self.headers = response.headers
		return response.headers

	def start_download(self):
		l = link.split('/')[-1]
    	print l

	def write_file(self, f_name):
		pass