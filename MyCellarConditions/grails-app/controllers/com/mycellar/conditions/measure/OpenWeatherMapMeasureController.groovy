package com.mycellar.conditions.measure

import org.springframework.dao.DataIntegrityViolationException

class OpenWeatherMapMeasureController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [openWeatherMapMeasureInstanceList: OpenWeatherMapMeasure.list(params), openWeatherMapMeasureInstanceTotal: OpenWeatherMapMeasure.count()]
    }

    def create() {
        [openWeatherMapMeasureInstance: new OpenWeatherMapMeasure(params)]
    }

    def save() {
        def openWeatherMapMeasureInstance = new OpenWeatherMapMeasure(params)
        if (!openWeatherMapMeasureInstance.save(flush: true)) {
            render(view: "create", model: [openWeatherMapMeasureInstance: openWeatherMapMeasureInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'openWeatherMapMeasure.label', default: 'OpenWeatherMapMeasure'), openWeatherMapMeasureInstance.id])
        redirect(action: "show", id: openWeatherMapMeasureInstance.id)
    }

    def show(Long id) {
        def openWeatherMapMeasureInstance = OpenWeatherMapMeasure.get(id)
        if (!openWeatherMapMeasureInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'openWeatherMapMeasure.label', default: 'OpenWeatherMapMeasure'), id])
            redirect(action: "list")
            return
        }

        [openWeatherMapMeasureInstance: openWeatherMapMeasureInstance]
    }

    def edit(Long id) {
        def openWeatherMapMeasureInstance = OpenWeatherMapMeasure.get(id)
        if (!openWeatherMapMeasureInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'openWeatherMapMeasure.label', default: 'OpenWeatherMapMeasure'), id])
            redirect(action: "list")
            return
        }

        [openWeatherMapMeasureInstance: openWeatherMapMeasureInstance]
    }

    def update(Long id, Long version) {
        def openWeatherMapMeasureInstance = OpenWeatherMapMeasure.get(id)
        if (!openWeatherMapMeasureInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'openWeatherMapMeasure.label', default: 'OpenWeatherMapMeasure'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (openWeatherMapMeasureInstance.version > version) {
                openWeatherMapMeasureInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'openWeatherMapMeasure.label', default: 'OpenWeatherMapMeasure')] as Object[],
                          "Another user has updated this OpenWeatherMapMeasure while you were editing")
                render(view: "edit", model: [openWeatherMapMeasureInstance: openWeatherMapMeasureInstance])
                return
            }
        }

        openWeatherMapMeasureInstance.properties = params

        if (!openWeatherMapMeasureInstance.save(flush: true)) {
            render(view: "edit", model: [openWeatherMapMeasureInstance: openWeatherMapMeasureInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'openWeatherMapMeasure.label', default: 'OpenWeatherMapMeasure'), openWeatherMapMeasureInstance.id])
        redirect(action: "show", id: openWeatherMapMeasureInstance.id)
    }

    def delete(Long id) {
        def openWeatherMapMeasureInstance = OpenWeatherMapMeasure.get(id)
        if (!openWeatherMapMeasureInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'openWeatherMapMeasure.label', default: 'OpenWeatherMapMeasure'), id])
            redirect(action: "list")
            return
        }

        try {
            openWeatherMapMeasureInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'openWeatherMapMeasure.label', default: 'OpenWeatherMapMeasure'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'openWeatherMapMeasure.label', default: 'OpenWeatherMapMeasure'), id])
            redirect(action: "show", id: id)
        }
    }
}
