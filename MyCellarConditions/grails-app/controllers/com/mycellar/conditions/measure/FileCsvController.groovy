package com.mycellar.conditions.measure


class FileCsvController {

	def index = {
		log.info "Uploaded file"
		}
		
	def delete = {
		redirect action:index
		}
}
