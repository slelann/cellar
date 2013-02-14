<%@ page import="com.mycellar.conditions.measure.Measure" %>



<div class="fieldcontain ${hasErrors(bean: measureInstance, field: 'measureDate', 'error')} required">
	<label for="measureDate">
		<g:message code="measure.measureDate.label" default="Measure Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="measureDate" precision="day"  value="${measureInstance?.measureDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: measureInstance, field: 'humidity', 'error')} required">
	<label for="humidity">
		<g:message code="measure.humidity.label" default="Humidity" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="humidity" from="${0..100}" class="range" required="" value="${fieldValue(bean: measureInstance, field: 'humidity')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: measureInstance, field: 'celsiusTemperature', 'error')} required">
	<label for="celsiusTemperature">
		<g:message code="measure.celsiusTemperature.label" default="Celsius Temperature" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="celsiusTemperature" value="${fieldValue(bean: measureInstance, field: 'celsiusTemperature')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: measureInstance, field: 'serie', 'error')} required">
	<label for="serie">
		<g:message code="measure.serie.label" default="Serie" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="serie" name="serie.id" from="${com.mycellar.conditions.measure.Serie.list()}" optionKey="id" required="" value="${measureInstance?.serie?.id}" class="many-to-one"/>
</div>

