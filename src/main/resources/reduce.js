function(key, values) {
	var maxPrice = 1;
	var res = {};
	values.forEach(function(value) {
		if (value >= maxPrice) {
			maxPrice = value;
		}
	});
	res.maxPrice = maxPrice;
	return res;
}