require 'rails_helper'

RSpec.describe Customer, type: :model do
  it 'should valid ' do
    customer = FactoryGirl.build(:customer)

    expect(customer).to be_valid
  end

  it 'should invalid without name' do
    customer = FactoryGirl.build(:customer, name: nil)

    expect(customer).not_to be_valid
  end

  it 'should invalid without city' do
    customer = FactoryGirl.build(:customer, city: nil)

    expect(customer).not_to be_valid
  end
end
