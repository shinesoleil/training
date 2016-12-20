class PricesController < ApplicationController
  def index
    prices = Price.all.where(product_id: params[:product_id])

    if prices.length == 0
      head 404
    else
      render json: prices, :status => 200
    end
  end

  def show
    price = Price.all.where(product_id: params[:product_id]).find_by_id(params[:id])

    if price
      render json: price, :status => 200
    else
      head 404
    end
  end

  def create
    if Product.exists?(params[:product_id])
      params[:price][:product_id] = params[:product_id]
    end
    price = Price.new(params.require(:price).permit(:amount, :product_id))

    if price.save
      render json: price, :status => 201
    else
      head 400
    end
  end
end
