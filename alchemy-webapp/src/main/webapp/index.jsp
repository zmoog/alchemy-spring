<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Alchemy</title>
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>

      <script type="text/x-handlebars">

        <div class="navbar">
          <div class="navbar-inner">
            <a class="brand" href="#">Alchemy</a>
            <ul class="nav">
              <li>{{#linkTo 'accounts'}}Accounts{{/linkTo}}</li>
              <li><a href="#">Transfer</a></li>
              <li>{{#linkTo 'about'}}About{{/linkTo}}</li>
            </ul>
          </div>
        </div>

        {{ outlet }}

      </script>


      <script type="text/x-handlebars" id="accounts">

        <div class="container-fluid">
          <div class="row-fluid">
            <div class="span3">
              <table>
                <thead>
                  <tr><td>Accounts</td></tr>
                </thead>
                {{#each model}}
                <tr>
                  <td>
                    {{#linkTo 'account' this}}{{name}} <small class="muted">By Author</small>{{/linkTo}}
                  </td>
                </tr>
                {{/each}}
              </table>
            </div>
            <div class="span9">{{outlet}}</div>
          </div>
        </div>

      </script>

      <script type="text/x-handlebars" id="account">
      
        <h2>{{ name }}</h2>
        <h3><small>Created {{date createdAt}}</small></h3>

        <hr>

        <div>{{ markdown description }}</div>

      </script>

      <script type="text/x-handlebars" id="about">
        <div class="about">
          <p>Bla bla</p>
        </div>
      </script>


      <script src="js/libs/jquery-1.9.1.js"></script>
      <script src="js/libs/handlebars-1.0.0-rc.3.js"></script>
      <script src="js/libs/ember-1.0.0-rc.3.js"></script>
      <script src="js/libs/ember-data-latest.min.js"></script>
      <script src="js/libs/bootstrap.min.js"></script>
      <script src="js/libs/moment.min.js"></script>
      <script src="js/libs/showdown.js"></script>
      <script src="js/app.js"></script>

    </body>
</html>
