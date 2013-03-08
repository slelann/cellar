<html>

<head>
<title><g:message code='spring.security.ui.login.title'/></title>
<meta name='layout' content='register'/>
<link href="css/bootstrap.css" rel="stylesheet" media="screen"> 
<link href='http://fonts.googleapis.com/css?family=Arvo:400,700' rel='stylesheet' type='text/css'>
<r:require modules="bootstrap"/>

<style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        font-family: 'Arvo', serif;
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
</style>
</head>

<body>
<script src="js/bootstrap.js"></script>
<p/>
    
<div class="container" style='text-align:center;'>
	<div class="form-signin">
	<form action='${postUrl}' method='POST' id="loginForm" name="loginForm" autocomplete='off'>

	<h2 class="form-signin-heading" align="left"><g:message code='spring.security.ui.login.signin'/></h2>

	<input class="input-block-level" type="text" name="j_username" id="username" size="20" placeholder="email"/>
	<input class="input-block-level" type="password" name="j_password" id="password" size="20" placeholder="Password"/>

	<label class="checkbox" for='remember_me'>
          <input type="checkbox" value="remember_me"><g:message code='spring.security.ui.login.rememberme'/>
    </label>
        	
	<div style="margin-bottom: 10px" >
		<!-- <label for='remember_me'><g:message code='spring.security.ui.login.rememberme'/></label> -->
		<g:link controller='register' action='forgotPassword'><g:message code='spring.security.ui.login.forgotPassword'/></g:link>
		<s2ui:linkButton elementId='register' controller='register' messageCode='spring.security.ui.login.register'/>
	</div>
	<div>
		<s2ui:submitButton class="btn btn-large btn-primary" elementId='loginButton' form='loginForm' messageCode='spring.security.ui.login.login'/>
	</div>
	</form>
	</div>
</div>

<script>
$(document).ready(function() {
	$('#username').focus();
});

<s2ui:initCheckboxes/>

</script>

</body>
</html>
