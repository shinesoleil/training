class ProductsController < ApplicationController
  def index
    products = Product.all

    if products.length == 0
      head 404
    else
      render json: products, status: 200
    end

  end

  def create
    product = Product.new(params)

    return product
  end
end
