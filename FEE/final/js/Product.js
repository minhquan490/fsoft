class Product {
    #productID;
    #productName;
    #manufacturer;
    #category;
    #price;

    constructor(productID){
        this.#productID = productID;
    }

    get getProductID(){
        return this.#productID;
    }

    get getProductName(){
        return this.#productName;
    }

    get getManufacturer(){
        return this.#manufacturer;
    }

    get getCategory(){
        return this.#category;
    }

    get getPrice(){
        return this.#price;
    }

    /**
     * @param {string} productID
     */
    set setProductID(productID){
        this.#productID = productID;
    }

    /**
     * @param {string} productName
     */
    set setProductName(productName){
        this.#productName = productName;
    }

    /**
     * @param {string} manufacturer
     */
    set setManufacturer(manufacturer){
        this.#manufacturer = manufacturer;
    }

    /**
     * @param {string} category
     */
    set setCategory(category){
        this.#category = category;
    }

    /**
     * @param {number} price
     */
    set setPrice(price){
        this.#price = price;
    }
}
export {Product};