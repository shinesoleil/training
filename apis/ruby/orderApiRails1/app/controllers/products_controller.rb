class ProductsController < ApplicationController

  def index
    products = Product.all

    render json:products, status => 200
  end
  #
  # def create
  #   :status => 201
  # end
end
