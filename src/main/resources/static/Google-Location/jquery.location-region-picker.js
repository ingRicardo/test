/**
 * jQuery Location Picker
 * Author: @bcole808
 * Based on the example provided at:
 *  https://developers.google.com/maps/documentation/javascript/examples/places-autocomplete
 */


;(function ( $, window, document, undefined ) {

	var loaded_scripts = {};

	 // The actual LocationRegionPicker constructor
	function LocationRegionPicker( element, options ) {
		var self = this;
		this.$element = $(element);

		this.options = $.extend( {}, options ) ;

 		this.loadScript('https://maps.googleapis.com/maps/api/js?key='+options.google_api_key+'&libraries=places', function() {
			self.init();
		});

	}

	LocationRegionPicker.prototype.init = function() {
		var self = this;

		this.map = new google.maps.Map(document.getElementById('map'));

		this.autocomplete = new google.maps.places.Autocomplete(this.$element[0], {
			types: (typeof this.options.types == 'array') ? this.options.types : [this.options.types]
		});

		this.autocomplete.bindTo('bounds', this.map);

		this.mapMarker = new google.maps.Marker({
		  map: this.map,
		});

		this.autocomplete.addListener('place_changed', function() {
			self.newLocationSelected();
		});

	};

	LocationRegionPicker.prototype.newLocationSelected = function() {

	  this.place = this.autocomplete.getPlace();

	  this.$element.next('.location-region-details').remove();

	  var $details = $('<div class="location-region-details" style="margin: 20px 0;"></div>');

		var country = this.getDetail('country');
	  if (country) {
		  $details.append('Country: <input type="text" class="form-control" name="country" value="'+country+'" disabled />');
		}

		var locality = this.getDetail('locality');
	  if (locality) {
		  $details.append('Loaclity: <input type="text" class="form-control" name="locality" value="'+locality+'" disabled />');
		}

		if (this.place.url) {
			$details.append('Map: <input type="text" class="form-control" name="map_url" value="'+this.place.url+'" disabled />');
		}

		if (this.place.geometry && this.place.geometry.location) {
			$details.append('Lat: <input type="text" class="form-control" name="latitude" value="'+this.place.geometry.location.lat()+'" disabled />');
			$details.append('Lon: <input type="text" class="form-control" name="longitude" value="'+this.place.geometry.location.lng()+'" disabled />');
		}

		this.$element.after($details);

	  this.updateMapView();

	};

	LocationRegionPicker.prototype.getDetail = function(type) {

		if (!this.place || !this.place.address_components) {
			return '';
		}

		return this.place.address_components.reduce(function(prev, current, index) {
			
			if (current.types.indexOf(type) != -1) {
				return current.long_name;
			}

			return prev;

		}, '');
	};

	LocationRegionPicker.prototype.updateMapView = function() {

		if (!this.place) {
			return;
		}

	  this.mapMarker.setVisible(false);

		if (!this.place.geometry) {
			return
		}

		if (this.place.geometry.viewport) {
	    this.map.fitBounds(this.place.geometry.viewport);
	  } else {
	    this.map.setCenter(this.place.geometry.location);
	    this.map.setZoom(17);  // Why 17? Because it looks good.
	  }

	  this.mapMarker.setIcon(({
	    url: this.place.icon,
	    size: new google.maps.Size(71, 71),
	    origin: new google.maps.Point(0, 0),
	    anchor: new google.maps.Point(17, 34),
	    scaledSize: new google.maps.Size(35, 35)
	  }));

	  this.mapMarker.setPosition(this.place.geometry.location);
	  this.mapMarker.setVisible(true);
	};

	LocationRegionPicker.prototype.loadScript = function(url, callback) {

		if (loaded_scripts[url]) {
			return;
		}

		var script = document.createElement( "script" )
		script.type = "text/javascript";
		if (script.readyState) {  //IE
			script.onreadystatechange = function() {
				if ( script.readyState === "loaded" || script.readyState === "complete" ) {
					script.onreadystatechange = null;
					loaded_scripts[url] = true;
					callback();
				}
			};
		} else {  //Others
			script.onload = function() {
				loaded_scripts[url] = true;
				callback();
			};
		}

		script.src = url;
		document.getElementsByTagName( "head" )[0].appendChild( script );
	};

	$.fn.LocationRegionPicker = function ( options ) {
		return this.each(function () {
			if (!$.data(this, 'LocationRegionPicker')) {
				$.data(this, 'LocationRegionPicker',
				new LocationRegionPicker( this, options ));
			}
		});
	};

})( jQuery, window, document );
