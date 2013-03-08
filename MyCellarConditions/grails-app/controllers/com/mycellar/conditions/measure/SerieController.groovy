package com.mycellar.conditions.measure

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException

class SerieController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

	def data(Long id)  {
		StringBuffer sb = new StringBuffer()
		sb.append("data ")
		sb.append(id)
		print sb.toString()
		
		def serieInstance = Serie.get(id)
		def map = [:]
		map.version = 0.5
		map.reqId = '0'
		map.status = 'ok'
		
		def columns = []
		columns << [label: 'Date', type: 'datetime']
		columns << [label: 'Temperature', type: 'number']
		columns << [label: 'Humidity', type: 'number']

		def data = serieInstance.measures
		def rows = []
		def cells
		data.each {
			cells = []
			cells << [v: it.measureDate] 
			cells << [v: it.celsiusTemperature]  
			//cells << [v: 'undefined'] << [v: 'undefined']
			cells << [v: it.humidity]
			//cells << [v: 'undefined'] << [v: 'undefined']
			rows << ['c': cells]

		}
		def table = [cols: columns, rows: rows]
		map.table = table
		render "google.visualization.Query.setResponse(" + (map as JSON) + ")"
	}
	
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [serieInstanceList: Serie.list(params), serieInstanceTotal: Serie.count()]
    }

    def create() {
        [serieInstance: new Serie(params)]
    }

    def save() {
        def serieInstance = new Serie(params)
        if (!serieInstance.save(flush: true)) {
            render(view: "create", model: [serieInstance: serieInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'serie.label', default: 'Serie'), serieInstance.id])
        redirect(action: "show", id: serieInstance.id)
    }

    def show(Long id) {
        def serieInstance = Serie.get(id)
        if (!serieInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'serie.label', default: 'Serie'), id])
            redirect(action: "list")
            return
        }

        [serieInstance: serieInstance]
    }

    def edit(Long id) {
        def serieInstance = Serie.get(id)
        if (!serieInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'serie.label', default: 'Serie'), id])
            redirect(action: "list")
            return
        }

        [serieInstance: serieInstance]
    }

    def update(Long id, Long version) {
        def serieInstance = Serie.get(id)
        if (!serieInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'serie.label', default: 'Serie'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (serieInstance.version > version) {
                serieInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'serie.label', default: 'Serie')] as Object[],
                          "Another user has updated this Serie while you were editing")
                render(view: "edit", model: [serieInstance: serieInstance])
                return
            }
        }

        serieInstance.properties = params

        if (!serieInstance.save(flush: true)) {
            render(view: "edit", model: [serieInstance: serieInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'serie.label', default: 'Serie'), serieInstance.id])
        redirect(action: "show", id: serieInstance.id)
    }

    def delete(Long id) {
        def serieInstance = Serie.get(id)
        if (!serieInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'serie.label', default: 'Serie'), id])
            redirect(action: "list")
            return
        }

        try {
            serieInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'serie.label', default: 'Serie'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'serie.label', default: 'Serie'), id])
            redirect(action: "show", id: id)
        }
    }
}
