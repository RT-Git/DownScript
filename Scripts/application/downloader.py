import getpass
import requests as rq

class Download:
	"""
	Status options
	0 : Not Started/IDLE
	1 :	Downloading
	2 :	Paused
	3 :	Finished
	4 :	Errors

	"""

	def __init__(self, l):
		self.user = getpass.getuser()
		self.d_name = l.split('/')[-1]
		self.link = l
		self.headers = "not-specified"
		self.l_path = "/home/"+self.user+"/Downloads/"
		self.w_path = "C:\\" # Update for windows
		self.status = 0

	def heads(self):
		response = rq.head(self.link)
		self.headers = response.headers
		return response.headers

	def start(self):
		f_name = self.d_name
		tmp = 0.0
		r = rq.get(self.link, stream=True)
		self.status = 1		#	Downloading
		size = r.headers['content-length']
		with open(self.l_path+f_name, 'wb') as f:
			print "Downloaded : "
			for chunk in r.iter_content(chunk_size=1024): 
				tmp = tmp + 1024.0
				print (tmp/float(size))*100.0,
				print "\r",
				if chunk:
					f.write(chunk)
			f.flush()
		print "\nDownload Complete!"