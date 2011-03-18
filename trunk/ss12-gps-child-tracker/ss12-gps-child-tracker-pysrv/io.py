import cgi

from google.appengine.api import users
from google.appengine.ext import webapp
from google.appengine.ext.webapp.util import run_wsgi_app
from google.appengine.ext import db

class Userinfo(db.Model):
    author = db.UserProperty()
    content = db.StringProperty()
    loc_lat = db.StringProperty()
    loc_long = db.StringProperty()
    date = db.DateTimeProperty(auto_now_add=True)

class MainPage(webapp.RequestHandler):
    def get(self):
        self.response.out.write('<html><body>')
        self.response.out.write("""
              <form action="/GID/sign" method="post">
                <div><textarea name="content" rows="1" cols="60"></textarea></div>
                <div><textarea name="latitude" rows="1" cols="60"></textarea></div>
                <div><textarea name="longitude" rows="1" cols="60"></textarea></div>
                <div><input type="submit" value="Sign Guestbook"></div>
              </form>
            </body>
          </html>""")

class Guestbook(webapp.RequestHandler):
    def post(self):
        userinfo = Userinfo(key_name=self.request.get('content'))

        if users.get_current_user():
            userinfo.author = users.get_current_user()

        userinfo.content = self.request.get('content')
        userinfo.loc_lat = self.request.get('latitude')
        userinfo.loc_long = self.request.get('longitude')
        userinfo.put()
        self.redirect('/GID/')

"""class Latitude(webapp.RequestHandler):
    def post(self):
        userinfo = Userinfo()

        if users.get_current_user():
            userinfo.author = users.get_current_user()

        userinfo.loc_lat = self.request.get('latitude')
        userinfo.put()
        self.redirect('/GID/')

class Longitude(webapp.RequestHandler):
    def post(self):
        userinfo = Userinfo()

        if users.get_current_user():
            userinfo.author = users.get_current_user()

        userinfo.loc_long = self.request.get('longitude')
        userinfo.put()
        self.redirect('/GID/')
"""
application = webapp.WSGIApplication(
                                     [('/GID/', MainPage),
                                      ('/GID/sign', Guestbook),
#                                      ('/GID/loc_lat', Latitude),
#                                      ('/GID/loc_long', Longitude)
                                      ],
                                     debug=True)

def main():
    run_wsgi_app(application)

if __name__ == "__main__":
    main()