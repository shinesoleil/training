require 'spec_helper'

describe 'Product' do

  it 'should invalid' do
    product = Product.new
    product.should be_valid
  end
end