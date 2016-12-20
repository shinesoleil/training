require 'rails_helper'

RSpec.describe CustomersController, type: :controller do

  it 'should find all customers' do
    customer = FactoryGirl.create(:customer)

    get :index
    res = JSON.parse(response.body)

    expect(response.status).to eq(200)
    expect(res.size).to be(1)
  end

  it 'should find all customers fails when nothing found' do
    get :index

    expect(response.status).to eq(404)
  end

  it 'should find customer by id success' do
    customer = FactoryGirl.create(:customer)

    get :show, params: {id: customer.id}
    res = JSON.parse(response.body)

    expect(response.status).to eq(200)
    expect(res["id"]).to eq(customer.id)
  end

  it 'should find customer by id fails when not found' do
    get :show, params: {id: 1}

    expect(response.status).to eq(404)
  end

  it 'should create customer success' do
    post :create, params: {customer: FactoryGirl.attributes_for(:customer)}

    expect(response.status).to eq(201)
    expect(response.get_header("Location")).to match(/customers\/\d/)
  end

  it 'should create customer success' do
    info = FactoryGirl.attributes_for(:customer)
    info[:name] = nil
    post :create, params: {customer: info}

    expect(response.status).to eq(400)
  end
end
