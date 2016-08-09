import requests as rq
from contextlib import closing

class download(object):

	link = ""
	headers = ""
	download_path_windows = "C:" 
	
	def __init__(self, link):
		self.link = link

	def get_headers(self):
		response = rq.head(self.link)
		self.headers = response.headers
		return response.headers

	def start(self):
		"progress bar, and run in thread"
		
		with closing(rq.get(self.link, stream=True)) as r:
			print r.headers['content-length']


	def write_file(self, f_name):
		pass