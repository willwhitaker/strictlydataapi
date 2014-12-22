var SeriesModel = Backbone.Model.extend({
        urlRoot: '/series',
    });

var series = new SeriesModel({id: 2004});

    series.fetch({
        success: function (user) {
            alert(user.toJSON());
        }
    })