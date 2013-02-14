package com.mycellar.conditions.measure



import org.junit.*
import grails.test.mixin.*

@TestFor(SerieController)
@Mock(Serie)
class SerieControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/serie/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.serieInstanceList.size() == 0
        assert model.serieInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.serieInstance != null
    }

    void testSave() {
        controller.save()

        assert model.serieInstance != null
        assert view == '/serie/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/serie/show/1'
        assert controller.flash.message != null
        assert Serie.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/serie/list'

        populateValidParams(params)
        def serie = new Serie(params)

        assert serie.save() != null

        params.id = serie.id

        def model = controller.show()

        assert model.serieInstance == serie
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/serie/list'

        populateValidParams(params)
        def serie = new Serie(params)

        assert serie.save() != null

        params.id = serie.id

        def model = controller.edit()

        assert model.serieInstance == serie
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/serie/list'

        response.reset()

        populateValidParams(params)
        def serie = new Serie(params)

        assert serie.save() != null

        // test invalid parameters in update
        params.id = serie.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/serie/edit"
        assert model.serieInstance != null

        serie.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/serie/show/$serie.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        serie.clearErrors()

        populateValidParams(params)
        params.id = serie.id
        params.version = -1
        controller.update()

        assert view == "/serie/edit"
        assert model.serieInstance != null
        assert model.serieInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/serie/list'

        response.reset()

        populateValidParams(params)
        def serie = new Serie(params)

        assert serie.save() != null
        assert Serie.count() == 1

        params.id = serie.id

        controller.delete()

        assert Serie.count() == 0
        assert Serie.get(serie.id) == null
        assert response.redirectedUrl == '/serie/list'
    }
}
