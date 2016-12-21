class CustomersController < ApplicationController
  def index
    costumers = Customer.all

    if costumers.length == 0
      head 404
    else
      render json: costumers, status: 200
    end
  end

  def create

  end
end
