const message = "500";
const myObj = {
    number: 100,
    oper: function (num) {
        return this.number + num;
    }
};


alert(myObj.oper(message));