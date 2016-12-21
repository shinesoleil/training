class PricesController < ApplicationController

  def index
    prices = Price.find_of_product(params[:product_id])

    if prices.size != 0
      render json: prices, :status => 200
    else
      head 404
    end
  end

  def show
    price = Price.find_of_product(params[:product_id]).find_by(params[:id])

    if price
      render json:price, :status => 200
    else
      head 404
    end
  end

  def create
    price = Price.new(params.require(:price).permit(:amount, :product_id))

    if price.save
      render json:price, :status => 201, location: price.route_url
    else
      head 400
    end
  end
end
