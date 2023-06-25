package com.pocket.demo01;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class LeetCodeTest {
    public int longestOnes(int[] nums, int k) {
        int left = 0, n = nums.length;
        int res = 0, temp = k;
        for(int right = 0; right<n; right++){
            if(nums[right] == 0 && temp > 0){
                temp--;
            }else if(nums[right] == 0){
                // while(temp < k && left < n){
                //     if(nums[left] == 0){
                //         temp++;
                //     }
                //     left++;
                // }
                while(nums[left] == 1 && left < right){
                    left++;
                }
                temp++;
                left++;
            }
            res = Math.max(res, right-left+1);
        }
        return res;
    }

    @Test
    public void at() {
        balancedString("QQWE");
    }

    public int balancedString(String s) {
        var charArray = s.toCharArray();
        var cnt = new int['X'];
        int n = s.length(), m = n / 4;
        for(var c: charArray){
            cnt[c]++;
        }
        if(cnt['Q'] == m && cnt['W'] == m && cnt['E'] == m && cnt['R'] == m) return 0;
        int res = 0, left = 0;
        for(int right = 0; right<n; right++){
            --cnt[charArray[right]];
            while(cnt['Q'] <= m && cnt['W'] <= m && cnt['E'] <= m && cnt['R'] <= m){
                res = Math.min(res, right - left + 1);
                ++cnt[charArray[left++]];
            }
        }
        return res;
    }
}
