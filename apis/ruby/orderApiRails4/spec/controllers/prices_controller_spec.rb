require 'rails_helper'

RSpec.describe PricesController, type: :controller do

  it 'get all' do
    price = FactoryGirl.create(:price)

    get :index, params: {:product_id => price[:product_id]}

    expect(response.status).to eq(200)
  end

  it 'get all fails' do
    get :index, params: {:product_id => 1}

    expect(response.status).to eq(404)
  end

  it 'get price by id' do
    price = FactoryGirl.create(:price)

    get :show, params: {:product_id => price[:product_id], :id => price[:id]}

    expect(response.status).to eq(200)
  end

  it 'get price by id fails' do
    product = FactoryGirl.create(:product)

    get :show, params: {:product_id => product[:id], :id => 999}

    expect(response.status).to eq(404)
  end

  it 'create price by id' do
    product = FactoryGirl.create(:product)
    post :create, params: {:product_id => product.id, :price => {:amount => 9.99, :product_id => product.id}}


    expect(response.status).to eq(201)
    expect(response.get_header("Location")).to match("products\/#{product.id}\/prices\/")
  end
end
