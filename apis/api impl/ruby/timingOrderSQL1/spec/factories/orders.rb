FactoryGirl.define do
  factory :order do
    customer
    total_price "9.99"
  end
end
