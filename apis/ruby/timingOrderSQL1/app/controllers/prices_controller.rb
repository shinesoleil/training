class PricesController < ApplicationController
  protect_from_forgery

  def index
    @prices = Price.where(product_id: params[:product_id])

    if @prices.size != 0
      render json: @prices, :status => 200
    else
      head 404
    end
  end

  def show
    @price = Price.where(product_id: params[:product_id]).find_by(id: params[:id])

    if @price
      render json: @price, :status => 200
    else
      head 404
    end
  end

  def create
    @product_id = params[:product_id]

    if Product.find_by(@product_id)
      params[:price][:product_id] = @product_id
    else
      head 404
    end

    @price = Price.new(params.require(:price).permit(:amount, :product_id))

    if @price.save
      render json: @price, :status => 201, :location => @price.route_url
    else
      head 400
    end
  end
end
