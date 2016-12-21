require 'rails_helper'

RSpec.describe CustomersController, type: :controller do

  it 'should list all customers' do
    customer = FactoryGirl.create(:customer)

    get :index
    res = JSON.parse(response.body)

    expect(response.status).to eq(200)
    expect(res.size).to eq(1)
    expect(res[0]["name"]).to eq(customer.name.to_s)
  end

  it 'should list all customers failure when nothing found' do
    get :index

    expect(response.status).to eq(404)
  end


  it 'should find customer by id' do
    customer = FactoryGirl.create(:customer)

    get :show, params: {id: customer.id}
    res = JSON.parse(response.body)

    expect(response.status).to eq(200)
    expect(res["name"]).to eq(customer.name.to_s)
  end

  it 'should find customer by id failure when not found' do
    get :show, params: {id: 1}

    expect(response.status).to eq(404)
  end

  it 'should create customer success' do
    customer = FactoryGirl.attributes_for(:customer)

    post :create, params: {customer: customer}
    res = JSON.parse(response.body)

    expect(response.status).to eq(201)
    expect(response.get_header("Location")).to match(/customers\/\d/)
    expect(res["name"]).to eq(customer[:name].to_s)
  end

  it 'should create customer failure with invalid info' do
    customer = FactoryGirl.attributes_for(:customer, :without_name)

    post :create, params: {customer: customer}

    expect(response.status).to eq(400)
  end

end
