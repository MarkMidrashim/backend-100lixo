#!-*- conding: utf8 -*-
#coding: utf-8

import os
import sys

class Main(object):
	"""
	Classe responavel por realizar build de microservices e gerenciar-los
	"""

	def __init__(self):
		self.project_path = os.path.abspath(os.getcwd())
		self.tag = "v1"
		self.list_projects = [
			"cst-db-mysql",
			"cst-ms-backend"
		]

	def build_api(self, project: str):
		"""
		Metodo responsavel por realizar o build dos microservices
		:param project:
		"""
		os.chdir("{}/API/{}".format(self.project_path, project))
		os.system("mvn clean package -DskipTests")
		os.chdir(self.project_path)

	def up(self):
		"""
		Metodo responsavel por chamar os metodos de build e executar o docker-compose
		"""
		try:
			if "--compile" in sys.argv[2]:
				for index, project in enumerate(list(filter(lambda i: "db" not in i or "amqp" not in i, self.list_projects))):
					self.build_api(project)
		except IndexError as ie:
			print("I'll not compile that")

		os.system("docker-compose up -d --build")

	def down(self):
		"""
		Metodo responsavel por realizar o down dos servicos
		"""
		os.system("docker-compose down")

	def run(self):
		"""
		Metodo responsavel por selecionar a acao de acordo com o parametro
		"""
		if sys.argv[1] == "up":
			self.up()
		elif sys.argv[1] == "down":
			self.down()
		elif sys.argv[1] == "rebuild":
			self.down()
			self.up()
		else:
			print("Command not found")


if __name__ == '__main__':
	try:
		main = Main()
		main.run()
	except Exception as e:
		print("-- ERROR --")
		print(e)
