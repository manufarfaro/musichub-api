<g:applyLayout name="main">
    <head>
        <title><g:if env="development">Grails Runtime Exception</g:if><g:else>Error</g:else></title>
        <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
    </head>
    <body>
    	<div class="container mh-container">
	    	<section class="row mh-alert-notfound">
	    		<div class="col-md-12">
	    			<div class="row">
	    				<div class="col-md-12">
	    					<div class="alert alert-danger" role="alert">
	    						<h3>Ha ocurrido un error (500):</h3>
						        <g:if env="development">
						            <g:if test="${Throwable.isInstance(exception)}">
						                <g:renderException exception="${exception}" />
						            </g:if>
						            <g:elseif test="${request.getAttribute('javax.servlet.error.exception')}">
						                <g:renderException exception="${request.getAttribute('javax.servlet.error.exception')}" />
						            </g:elseif>
						            <g:else>
						                <ul class="errors">
						                    <li><strong>Exception:</strong> ${exception}</li>
						                    <li><strong>Message:</strong> ${message}</li>
						                    <li><strong>Path:</strong> ${path}</li>
						                </ul>
						            </g:else>
						        </g:if>
						        <g:else>
						        	<h4>Se ha producido un error, por favor intente nuevamente en unos momentos, si el error persiste porfavor envíenos un mail con su inconveniente para poder asistirlo. <br/> Gracias.</h4>
						        </g:else>
	    					</div>
	    				</div>
	    			</div>
	    		</div>
	    	</section>
	    </div>
    </body>
</g:applyLayout>
