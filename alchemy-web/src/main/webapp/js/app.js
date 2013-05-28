App = Ember.Application.create();

App.Store = DS.Store.extend({
	revision: 12,
	adapter: 'DS.FixtureAdapter'
});

App.Router.map(function() {
  this.resource('about');
  this.resource('accounts', function() {
  	this.resource('account', { path: ':account_id'});
  });
});

App.AccountsRoute = Ember.Route.extend({
	model: function() {
		return App.Account.find();
	}
});

App.Account = DS.Model.extend({
	name: DS.attr('string'),
	description: DS.attr('string'),
	createdAt: DS.attr('date')
});

App.Account.FIXTURES = [{
	id: 1,
	name: 'Fineco',
	description: 'The real *important* bank account.',
	createdAt: new Date('2013-05-09')
},{
	id: 2,
	name: 'Opo wallet',
	description: "#Wallet The wallet in your pocket.",
	createdAt: new Date('2013-05-09')
},{
	id: 3,
	name: 'Libri e riviste',
	description: 'Something like magazines and books.',
	createdAt: new Date('2013-05-09')
}];


Ember.Handlebars.registerBoundHelper('date', function(date) {
	return moment(date).fromNow();
});

var showdown = new Showdown.converter();

Ember.Handlebars.registerBoundHelper('markdown', function(input) {

	console.log(input);

	return new Ember.Handlebars.SafeString(showdown.makeHtml(input));
});