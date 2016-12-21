require 'rails_helper'

RSpec.describe Customer, type: :model do
  it 'valid info' do
    customer = FactoryGirl.build(:customer)

    expect(customer).to be_valid
  end

  it 'invalid without name' do
    customer = FactoryGirl.build(:customer, :without_name)

    expect(customer).not_to be_valid
  end

  it 'invalid without city' do
    customer = FactoryGirl.build(:customer, :without_city)

    expect(customer).not_to be_valid
  end
end
