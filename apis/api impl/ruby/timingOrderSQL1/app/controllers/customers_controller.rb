class CustomersController < ApplicationController
  def index
    @customers = Customer.all

    if @customers.size != 0
      render json: @customers, :status => 200
    else
      head 404
    end
  end

  def show
    @customer = Customer.find_by(id: params[:id])

    if @customer
      render json: @customer, :status => 200
    else
      head 404
    end
  end

  def create
    @customer = Customer.new(params.require(:customer).permit(:name, :city))

    if @customer.save
      render json: @customer, :status => 201, :location => @customer.route_url
    else
      head 400
    end
  end
end
