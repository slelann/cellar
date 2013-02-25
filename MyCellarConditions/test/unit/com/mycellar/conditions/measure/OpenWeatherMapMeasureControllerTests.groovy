package com.mycellar.conditions.measure



import org.junit.*
import grails.test.mixin.*

@TestFor(OpenWeatherMapMeasureController)
@Mock(OpenWeatherMapMeasure)
class OpenWeatherMapMeasureControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/openWeatherMapMeasure/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.openWeatherMapMeasureInstanceList.size() == 0
        assert model.openWeatherMapMeasureInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.openWeatherMapMeasureInstance != null
    }

    void testSave() {
        controller.save()

        assert model.openWeatherMapMeasureInstance != null
        assert view == '/openWeatherMapMeasure/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/openWeatherMapMeasure/show/1'
        assert controller.flash.message != null
        assert OpenWeatherMapMeasure.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/openWeatherMapMeasure/list'

        populateValidParams(params)
        def openWeatherMapMeasure = new OpenWeatherMapMeasure(params)

        assert openWeatherMapMeasure.save() != null

        params.id = openWeatherMapMeasure.id

        def model = controller.show()

        assert model.openWeatherMapMeasureInstance == openWeatherMapMeasure
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/openWeatherMapMeasure/list'

        populateValidParams(params)
        def openWeatherMapMeasure = new OpenWeatherMapMeasure(params)

        assert openWeatherMapMeasure.save() != null

        params.id = openWeatherMapMeasure.id

        def model = controller.edit()

        assert model.openWeatherMapMeasureInstance == openWeatherMapMeasure
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/openWeatherMapMeasure/list'

        response.reset()

        populateValidParams(params)
        def openWeatherMapMeasure = new OpenWeatherMapMeasure(params)

        assert openWeatherMapMeasure.save() != null

        // test invalid parameters in update
        params.id = openWeatherMapMeasure.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/openWeatherMapMeasure/edit"
        assert model.openWeatherMapMeasureInstance != null

        openWeatherMapMeasure.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/openWeatherMapMeasure/show/$openWeatherMapMeasure.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        openWeatherMapMeasure.clearErrors()

        populateValidParams(params)
        params.id = openWeatherMapMeasure.id
        params.version = -1
        controller.update()

        assert view == "/openWeatherMapMeasure/edit"
        assert model.openWeatherMapMeasureInstance != null
        assert model.openWeatherMapMeasureInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/openWeatherMapMeasure/list'

        response.reset()

        populateValidParams(params)
        def openWeatherMapMeasure = new OpenWeatherMapMeasure(params)

        assert openWeatherMapMeasure.save() != null
        assert OpenWeatherMapMeasure.count() == 1

        params.id = openWeatherMapMeasure.id

        controller.delete()

        assert OpenWeatherMapMeasure.count() == 0
        assert OpenWeatherMapMeasure.get(openWeatherMapMeasure.id) == null
        assert response.redirectedUrl == '/openWeatherMapMeasure/list'
    }
}
