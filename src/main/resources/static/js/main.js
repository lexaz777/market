/*var products = [
	{id: 1,title: 'Macbook Pro', price: 2500.00, qty: 1, image: 'http://lorempixel.com/150/150/'},
	{id: 2,title: 'Asus ROG Gaming',price: 1000.00, qty: 1,image: 'http://lorempixel.com/150/150/'},
	{id: 3,title: 'Amazon Kindle',price: 150.00,qty: 1,image: 'http://lorempixel.com/150/150/'},
	{id: 4,title: 'Another Product',price: 10, qty: 1, image: 'http://lorempixel.com/150/150/'},
];*/

/*function formatNumber(n, c, d, t){
	var c = isNaN(c = Math.abs(c)) ? 2 : c,
			d = d === undefined ? '.' : d,
			t = t === undefined ? ',' : t,
			s = n < 0 ? '-' : '',
			i = String(parseInt(n = Math.abs(Number(n) || 0).toFixed(c))),
			j = (j = i.length) > 3 ? j % 3 : 0;
	return s + (j ? i.substr(0, j) + t : '') + i.substr(j).replace(/(\d{3})(?=\d)/g, '$1' + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : '');
};*/

/*Vue.prototype.filter('formatCurrency', function (value) {
  return formatNumber(value, 2, '.', ',');
});*/

/*Vue.component('shopping-cart', {
  props: ['items'],

  computed: {
    Total: function() {
      var total = 0;
      this.items.forEach(item => {
        total += (item.price * item.qty);
      });
      return total;
    }
  },

  methods: {
    removeItem(index) {
      this.items.splice(index, 1)
    }
  }
})*/

var vm = new Vue({
    el: '#app',
    data: {
        items: [],
        totalPrice: 0,
        postBody: null
    },
    mounted() {
        axios
            .get('/market/api/cart_items')
            .then(response => {
                this.items = response.data.cartItems;
                this.totalPrice = response.data.totalPrice;
            })
            .catch(error => {
                console.log(error);
                this.errored = true;
            })
            .finally(() => (this.loading = false));
    },
    methods: {
        imagePath: function (index) {
            return '/market/img/' + this.items[index].product.photoName;
        },
        removeItem(index, id) {
            axios
                .delete('/market/api/cart_items/' + id)
                .then(response => {
                    this.items = response.data.cartItems;
                    this.totalPrice = response.data.totalPrice;
                })
                .catch(error => {
                    console.log(error);
                    this.errored = true;
                })
                .finally(() => (this.loading = false));
        },
        setQty(index, id) {
            console.log("id=" + id);
            var carpost = {
                id: id,
                quantity: this.items[index].quantity
            };
            this.postBody = JSON.stringify(carpost);
            axios
                .post('/market/api/cart_items_quantity/', this.postBody, {
                    headers: {
                        'Content-Type': 'application/json',
                    }
                })
                .then(response => {
                    this.items = response.data.cartItems;
                    this.totalPrice = response.data.totalPrice;
                })
                .catch(error => {
                    console.log(error);
                    this.errored = true;
                }).finally(() => (this.loading = false));
        },
        updated: function () {
            console.log('updated()');
        }
    },
    computed: {

    }
})


$(document).ready(function () {

    $('.item').on('click', function (event) {
        var id = $(this).attr('data');
        //var id = getURLParameter(url,'id');
        console.log("id = " + id);
        $.ajax({
            url: "/market/api/cart_items",
            type: "POST",
            data: {
                "id": id
            }
        }).done(function () {
            alert("Product added to Cart");
        });
    });

    $('.table').on('click', '.btn-danger', function (event) {
        var element = $(this);
        var id = element.attr('id');
        console.log("id = " + id);
        $.ajax({
            url: "/market/api/cart_items",
            type: "DELETE",
            data: {
                "id": id
            }
        }).done(function () {
            var parent_row = element.parents()[1];
            parent_row.remove();
            alert("Product deleted from Cart");
        });
    });
});