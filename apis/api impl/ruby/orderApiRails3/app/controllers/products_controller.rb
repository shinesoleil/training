class ProductsController < ApplicationController
  protect_from_forgery

  def index
    products = Product.all
    render json: products, :status => 200
  end

  def show
    product = Product.find_by_id(params[:id])

    if product
      render json: product.to_json(except: [:created_at, :updated_at], include: :prices), :status => 200
    else
      head 404
    end
  end

  def create
    product = Product.new(params.require(:product).permit(:name))

    if product.save
      render json: product, :status => 201, :location => product.route_url
    else
      head 400
    end
  end
end
