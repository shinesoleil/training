class ProductsController < ApplicationController
  protect_from_forgery

  def index
    @products = Product.all

    if @products.size != 0
      render json: @products, :status => 200
    else
      head 404
    end
  end

  def show
    @product = Product.find_by(id: params[:id])

    if @product
      render json: @product, :status => 200
    else
      head 404
    end
  end

  def create
    @product = Product.new(params.require(:product).permit(:name))

    if @product.save
      render json: @product, :status => 201, :location => @product.route_url
    else
      head 400
    end
  end
end
