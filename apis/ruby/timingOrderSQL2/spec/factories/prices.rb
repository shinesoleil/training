FactoryGirl.define do
  factory :price do
    product
    amount "9.99"

    trait :without_amount do
      amount nil
    end
  end
end
