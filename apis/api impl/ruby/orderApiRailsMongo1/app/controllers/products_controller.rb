class ProductsController < ApplicationController
  protect_from_forgery

  def index
    @products = Product.all

    render json: @products, :status => 200
  end

  def create
    @product = Product.new(params.require(:product).permit(:name))

    if @product.save
      render json:@product, :status => 201
    else
      head 400
    end
  end
end
